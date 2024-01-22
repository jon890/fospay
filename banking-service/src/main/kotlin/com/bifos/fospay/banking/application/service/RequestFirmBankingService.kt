package com.bifos.fospay.banking.application.service

import com.bifos.fospay.banking.adapter.axon.command.AxonCreateFirmBankingRequestCommand
import com.bifos.fospay.banking.adapter.axon.command.AxonUpdateFirmBankingRequestCommand
import com.bifos.fospay.banking.adapter.out.external.bank.ExternalFirmBankingRequest
import com.bifos.fospay.banking.adapter.out.persistence.FirmBankingRequestMapper
import com.bifos.fospay.banking.application.port.`in`.RequestFirmBankingCommand
import com.bifos.fospay.banking.application.port.`in`.RequestFirmBankingUseCase
import com.bifos.fospay.banking.application.port.`in`.UpdateFirmBankingCommand
import com.bifos.fospay.banking.application.port.`in`.UpdateFirmBankingUseCase
import com.bifos.fospay.banking.application.port.out.RequestExternalFirmBankingPort
import com.bifos.fospay.banking.application.port.out.RequestFirmBankingPort
import com.bifos.fospay.banking.domain.FirmBankingRequest
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class RequestFirmBankingService(
    private val requestFirmBankingPort: RequestFirmBankingPort,
    private val firmBankingRequestMapper: FirmBankingRequestMapper,
    private val requestExternalFirmBankingPort: RequestExternalFirmBankingPort,
    private val commandGateway: CommandGateway
) : RequestFirmBankingUseCase, UpdateFirmBankingUseCase {

    override fun requestFirmBanking(command: RequestFirmBankingCommand): FirmBankingRequest {
        // Business Logic

        // a -> b 계좌

        // 1. 요청에 대해 정보를 먼저 write. "요청" 상태로
        val uuid = UUID.randomUUID()
        val entity = requestFirmBankingPort.createFirmBankingRequest(
            command.fromBankName!!,
            command.fromBankAccountNumber!!,
            command.toBankName!!,
            command.toBankAccountNumber!!,
            command.moneyAmount!!,
            "0",
            uuid,
            null
        )

        // 2. 외부 은행에 펌뱅킹 요청
        val firmBankingResult = requestExternalFirmBankingPort.requestExternalFirmBanking(
            ExternalFirmBankingRequest(
                command.fromBankName,
                command.fromBankAccountNumber,
                command.toBankName,
                command.toBankAccountNumber,
                command.moneyAmount
            )
        )

        // 3. 결과에 따라서 1번에서 작성했던 FirmBankingRquest 정보를 update
        if (firmBankingResult.resultCode == 0) {
            entity.firmBankingStatus = "1"
        } else {
            entity.firmBankingStatus = "2"
        }

        // save
        requestFirmBankingPort.modifyFirmBankingRequest(entity)

        // 4. 결과를 리턴
        return firmBankingRequestMapper.mapToDomainEntity(entity)
    }

    override fun requestFirmBankingByEvent(command: RequestFirmBankingCommand) {
        // Command -> Event Sourcing
        val axonCommand = AxonCreateFirmBankingRequestCommand(
            fromBankName = command.fromBankName,
            fromBankAccountNumber = command.fromBankAccountNumber,
            toBankName = command.toBankName,
            toBankAccountNumber = command.toBankAccountNumber,
            moneyAmount = command.moneyAmount
        )

        commandGateway.send<String>(axonCommand).whenComplete { result, throwable ->
            if (throwable != null) {
                throwable.printStackTrace()
            } else {
                val uuid = UUID.randomUUID()
                val entity = requestFirmBankingPort.createFirmBankingRequest(
                    command.fromBankName!!,
                    command.fromBankAccountNumber!!,
                    command.toBankName!!,
                    command.toBankAccountNumber!!,
                    command.moneyAmount!!,
                    "0",
                    uuid,
                    result
                )

                // 2. 외부 은행에 펌뱅킹 요청
                val firmBankingResult = requestExternalFirmBankingPort.requestExternalFirmBanking(
                    ExternalFirmBankingRequest(
                        command.fromBankName,
                        command.fromBankAccountNumber,
                        command.toBankName,
                        command.toBankAccountNumber,
                        command.moneyAmount
                    )
                )

                // 3. 결과에 따라서 1번에서 작성했던 FirmBankingRquest 정보를 update
                if (firmBankingResult.resultCode == 0) {
                    entity.firmBankingStatus = "1"
                } else {
                    entity.firmBankingStatus = "2"
                }

                // save
                requestFirmBankingPort.modifyFirmBankingRequest(entity)
            }
        }
    }

    override fun requestFirmBankingByEvent(command: UpdateFirmBankingCommand) {
        // Command
        val axonCommand = AxonUpdateFirmBankingRequestCommand(
            command.aggregateIdentifier, command.status
        )

        commandGateway.send<String>(axonCommand).whenComplete { result, throwable ->
            if (throwable != null) {
                throwable.printStackTrace()
            } else {
                val entity = requestFirmBankingPort.getFirmBankingRequest(command.aggregateIdentifier!!)
                    ?: throw RuntimeException("aggregate not found")

                // status 변경으로 인한 외부 은행과의 커뮤니케이션
                // if rollback -> 0, status 변경도 해주겠지만
                // + 기존 펌뱅킹 정보에서 from <-> to 가 변경된 펌뱅킹을 요청하는 새로운 요청

                entity.firmBankingStatus = command.status!!.toString()
                requestFirmBankingPort.modifyFirmBankingRequest(entity)
            }
        }
    }
}
