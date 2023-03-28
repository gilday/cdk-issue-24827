package com.github.gilday.cdk;

import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.NestedStack;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.Stage;
import software.amazon.awscdk.StageProps;
import software.amazon.awscdk.services.ecr.assets.TarballImageAsset;
import software.amazon.awscdk.services.ecr.assets.TarballImageAssetProps;
import software.constructs.Construct;

public class MyServerlessApplication {

  public static void main(String[] args) {
    final var app = new App();
    final var account = System.getenv("CDK_DEFAULT_ACCOUNT");
    final var region = System.getenv("CDK_DEFAULT_REGION");
    if (account == null || region == null) {
      throw new IllegalStateException(
          "CDK_DEFAULT_ACCOUNT and CDK_DEFAULT_REGION must be set in the environment. These environments variables are set automatically when running cdk commands. This program is only intended to be run by cdk commands.");
    }
    final var environment = Environment.builder().account(account).region(region).build();
    new MyStage(app, "development", StageProps.builder().env(environment).build());
    app.synth();
  }

  static class MyNestedStack extends NestedStack {
    MyNestedStack(final Construct scope, final String id) {
      super(scope, id);
      new TarballImageAsset(
          this,
          "image",
          TarballImageAssetProps.builder()
              .tarballFile(
                  "./my-serverless-application/build/assets/my-custom-lambda-runtime-image.tar")
              .build());
    }
  }

  static class MyStack extends Stack {
    MyStack(final Construct scope, final String id) {
      super(scope, id);
      new MyNestedStack(this, "nested-stack");
    }
  }

  static class MyStage extends Stage {
    MyStage(final Construct scope, final String id, final StageProps props) {
      super(scope, id, props);
      new MyStack(this, "stack");
    }
  }
}
