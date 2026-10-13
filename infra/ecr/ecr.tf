resource "aws_ecr_repository" "tracker" {
  name = var.repo_name
  tags = {
    "project" : var.project
    "environment" : var.environment
  }
}