.PHONY: run

run:
	@echo "Running ./gradlew docker"
	@./gradlew docker
	@echo "Running docker compose up -d"
	@docker compose up -d
	@echo "Docker Image Prune"
	@yes | docker image prune