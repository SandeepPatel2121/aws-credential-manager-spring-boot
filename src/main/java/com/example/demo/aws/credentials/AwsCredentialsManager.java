package com.example.demo.aws.credentials;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.*;
import com.example.demo.models.DataModel;
import com.google.gson.Gson;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@Configuration
public class AwsCredentialsManager {

    public static DataModel getSecret() {
        DataModel dataModel = null;
        String secretName = "demo";
        String region = "us-east-1";

        // Create a Secrets Manager client
        AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("AKIARZBXCMI7CGYWFQFF", "dB4BVyS3sEPJUoli5PeJ9r9aznJJ6ai1G452xb60")))
                .build();

        String secret, decodedBinarySecret;
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(secretName);
        GetSecretValueResult getSecretValueResult = null;

        try {
            getSecretValueResult = client.getSecretValue(getSecretValueRequest);
            if (getSecretValueResult != null) {
                secret = getSecretValueResult.getSecretString();
                if (secret != null) {
                    Gson gson = new Gson();
                    dataModel = gson.fromJson(secret, DataModel.class);
                }
            } else {
                decodedBinarySecret = new String(Base64.getDecoder().decode(getSecretValueResult.getSecretBinary()).array());
            }
        } catch (DecryptionFailureException | InternalServiceErrorException | InvalidParameterException | InvalidRequestException | ResourceNotFoundException ex) {
            System.out.println("EXCEPTION : " + ex.getMessage());
        }
        return dataModel;
    }

    public static void getAppSecrets() {
        String secretName = "demo-key";
        String region = "us-east-1";

        // Create a Secrets Manager client
        AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("AKIARZBXCMI7CGYWFQFF", "dB4BVyS3sEPJUoli5PeJ9r9aznJJ6ai1G452xb60")))
                .build();

        String secret, decodedBinarySecret;
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(secretName);
        GetSecretValueResult getSecretValueResult = null;

        try {
            getSecretValueResult = client.getSecretValue(getSecretValueRequest);
            if (getSecretValueResult != null) {
                secret = getSecretValueResult.getSecretString();
                System.out.println("SECRETS : " + secret);
            } else {
                decodedBinarySecret = new String(Base64.getDecoder().decode(getSecretValueResult.getSecretBinary()).array());
            }
        } catch (DecryptionFailureException | InternalServiceErrorException | InvalidParameterException | InvalidRequestException | ResourceNotFoundException ex) {
            System.out.println("EXCEPTION : " + ex.getMessage());
        }
    }

}
