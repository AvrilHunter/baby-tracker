# Project

Due to being on maternity leave with my baby, I decided to create a mini application which will track my babies feeding schedule
making it easy to identify when she next requires a feed.

I used Java Spring to create the backend API and will review adding a front end later in the project. 
As I am keen to progress my devops career I have reviewed deploying the application locally onto a minikube cluster and my next tickets are to review deploying in the AWS cloud.

# Tickets

## Ticket 1 - initial set up of repo **DONE**

## Ticket 2 - create Java Spring API **DONE**
- Create endpoints
    - POST /feed
        - to add date, start and end time
    - GET /feeds
        - to get all feeds logged
- Add spring testing for the application
- Install bruno
- Manually test endpoints and add collection to the repo

## Ticket 3 - create base Java front end
- Review which technology to utalise

## Ticket 4 - devOps to run locally **DONE**
- Create dockerfile
- Install minikube
- Create helm charts
- Instructions how to deploy locally onto minikube
- Update README with how to deploy application - write a script file for these actions

## Task 1 - Update README for project and API **DONE**

## Ticket 7 - Deploying on AWS **DONE**

- Log onto AWS console, set up log in for CLI
- Create terraform infra for an ECR
- Create code to push image to the ECR

## Ticket 8 - CI/CD to automatically update the image
- Review github actions to automatically update the image

## Ticket 9 - review creating an EKS, or alternatively deploy onto an EC2

## Ticket 10 - review spring application improving testing and functionality

## Ticket 11 - review adding pre-commit hooks
- Terraform fmt