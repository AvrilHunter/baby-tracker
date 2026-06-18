#!/bin/bash

aws ecr get-login-password --region eu-west-2 | crane auth login --username AWS --password-stdin 390402535867.dkr.ecr.eu-west-2.amazonaws.com

crane push tracker.tar 390402535867.dkr.ecr.eu-west-2.amazonaws.com/baby_tracker:0.0.1
