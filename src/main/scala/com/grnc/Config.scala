package com.grnc

import java.io.FileInputStream
import java.security.PrivateKey

import com.google.auth.oauth2.ServiceAccountCredentials

trait Config {
  private val googleCredentials: ServiceAccountCredentials =
    ServiceAccountCredentials.fromStream(new FileInputStream("[SERVICE_ACCOUNT_JSON_FILE_PATH]"))

  val projectId: String = googleCredentials.getProjectId
  val clientEmail: String = googleCredentials.getClientEmail
  val privateKey: PrivateKey = googleCredentials.getPrivateKey

}
