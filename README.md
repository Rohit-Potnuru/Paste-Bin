# Paste-Bin
This Repository is implementing PasteBin in spring boot application

Paste Bin System Overview

[//]: # (![img.png]&#40;ReadMe/img.png&#41;)


Get PasteBinData Service

# PasteBinServiceCDK
To deploy changes using CDK, please follow the below steps. 
1. Create an AWS Profile in your local using AWS CLI.
   * `aws configure --profile <profile-name>`
2. Verify AWS CLI Configuration
   * `aws configure list --profile <profile-name>`
3. Prepare your AWS environment for deploying CDK applications
   * `cdk boostrap` - sets up the necessary resources and permissions to enable CDK deployments to function properly.
4. Deploy the created or modified resources in CDK
   * `AWS_PROFILE=<profile-name> cdk deploy`
