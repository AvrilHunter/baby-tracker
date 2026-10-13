module "ecr" {
  source      = "./ecr"
  repo_name   = "baby_tracker"
  project     = "tracker"
  environment = "dev"
}