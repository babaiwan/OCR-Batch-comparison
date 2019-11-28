What you need
1.	JRE-1.8
Java 字节码文件的运行环境
2.	OCR
安装地址：https://github.com/UB-Mannheim/tesseract/wiki
选择x86或者x64 	(according to your machine.
How to use:
	You can learn how to use it according to a video I transcribe.

1.	确保你已经安装了以上环境(use “tesseract -v” in cmd to test)（需要配置环境变量）



系统变量-Path:					C:Program File\Tesseract-OCR



系统变量-新增-TESSDATA_PREFIX:	C:\Program Files\Tesseract-OCR\tessdata


2.	Cmd中进入到jar文件夹，输入”java -jar LanguageTool.jar” to Start





How to improve
	This only provide a very simple demo，if u wan to improve the accuracy.
You need to learn something about OCR and training it in your machine.(for now,it only support English and not translate 100% precisely)
