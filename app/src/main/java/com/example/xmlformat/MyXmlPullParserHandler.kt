package com.example.xmlformat

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class MyXmlPullParserHandler {
    private val students = ArrayList<Student>()
    private var text: String? = null
    private var student:Student? = null

    fun parse(inputStream: InputStream): List<Student> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name
                when (eventType) {
                    //first we will create student list when parsing start
                    XmlPullParser.START_TAG-> if(tagName.equals("student", ignoreCase = true)) {
                     //declared the student in the beginning of <Student>
                        student = Student() }
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> when {
                        tagName.equals("id", ignoreCase = true) -> {
                            student!!.id   =  Integer.parseInt(text)
                        }
                        tagName.equals("name", ignoreCase = true) -> {
                            student!!.name = text.toString()
                        }
                        tagName.equals("marks", ignoreCase = true) -> {
                            student!!.marks = text!!.toFloat()
                        } //then add the student to the list of students
                        else -> student?.let { students.add(it) }
                    }

                    else -> {
                    }
                }
                eventType = parser.next()
            }

        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return students
    }
}