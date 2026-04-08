package com.nebula1.SpringAiCode.servise;

import java.util.ArrayList;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ChatClient chatClient;

    public ReviewService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public List<String> generateReviews(String business, String service) {

    	String prompt = """
    			You are a professional Google review writer.

    			Generate exactly 6 high-quality Google reviews.

    			Business Name: %s
    			Service: %s

    			Requirements:
    			- Each review must be 2-3 sentences
    			- Simple and natural English
    			- All reviews must be unique
    			- Positive customer experience
    			- No numbering inside text

    			Output Format:
    			1. <review>
    			2. <review> 
    			3. <review>
    			4. <review>
    			5. <review>
    			6. <review>
    			""".formatted(business, service);

        String response = chatClient.prompt()
                .user(prompt)
                .call() 
                .content();

        // 🔥 Split reviews properly
        String[] parts = response.split("\\n\\d+\\. ");

        List<String> reviews = new ArrayList<>();

        for (String part : parts) {
            if (!part.trim().isEmpty()) {
                reviews.add(part.trim());
            }
        }

        return reviews;
    }
}