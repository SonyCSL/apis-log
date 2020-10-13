def version = args[0]
def outFileName = args[1]

def files = [
  [path:"target/apis-log-${version}-fat.jar", name:"apis-log-${version}-fat.jar", md5:"yes"],
  [path:"src/main/resources/stop-kill.sh", name:"stop-kill.sh", md5:"yes"],
  [path:"src/main/resources/verify.sh", name:"verify.sh", md5:"yes"],
  [path:"start.sh", name:"start.sh", md5:"no"],
  [path:"stop.sh", name:"stop.sh", md5:"no"],
  [path:"kill.sh", name:"kill.sh", md5:"no"],
  [path:"config.json", name:"config.json", md5:"no"],
  [path:"logging.properties", name:"logging.properties", md5:"no"],
  [path:"verify.info", name:"verify.info", md5:"no"]
]

def outFile = new File("${outFileName}")
outFile.withWriter { writer->
  files.each {
    if (it["md5"] == "yes") {
      def execCommand = "md5 ${it["path"]}"
      def execResult = "${execCommand}".execute().text
      def md5value = "${execResult}".split(/\s+/)[3]
      writer.write("${it["name"]}:md5hash:${md5value}\n")
    } else {
      writer.write("${it["name"]}:\n")
    }
  }
}
