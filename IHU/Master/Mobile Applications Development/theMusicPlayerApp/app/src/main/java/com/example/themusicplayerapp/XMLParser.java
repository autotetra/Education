package com.example.themusicplayerapp;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.InputSource;

public class XMLParser {

    public List<Song> parse(String xml) {
        List<Song> songs = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xml)));
            NodeList songNodes = doc.getElementsByTagName("song");

            for (int i = 0; i < songNodes.getLength(); i++) {
                Element songElement = (Element) songNodes.item(i);

                String title = songElement.getElementsByTagName("title").item(0).getTextContent();
                String artist = songElement.getElementsByTagName("artist").item(0).getTextContent();
                String url = songElement.getElementsByTagName("url").item(0).getTextContent();
                String duration = songElement.getElementsByTagName("duration").item(0).getTextContent();

                songs.add(new Song(title, artist, url, duration));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return songs;
    }
}
