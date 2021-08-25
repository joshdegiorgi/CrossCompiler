package io

import java.net.URL


object IO {


  def loadResource(path: String): URL = {
    getClass.getResource(path)
  }

}
