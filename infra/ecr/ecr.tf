resource "aws_ecr_repository" "tracker" {
  name = var.repo_name
  tags = {
    "project" : "baby tracker"
  }
}