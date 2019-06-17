package com.grnc

import java.security.spec.PKCS8EncodedKeySpec
import java.security.{KeyFactory, PrivateKey}

import com.google.common.io.BaseEncoding


object Helper {

  def privateKeyToString(privateKey: PrivateKey): String = {
    val packed = KeyFactory
      .getInstance("RSA")
      .getKeySpec(privateKey, classOf[PKCS8EncodedKeySpec])
      .getEncoded

    val returnVal = BaseEncoding.base64().encode(packed)
    java.util.Arrays.fill(packed, 0.asInstanceOf[Byte])
    returnVal
  }

}
