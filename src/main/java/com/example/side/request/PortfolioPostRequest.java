package com.example.side.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioPostRequest {
    private Long id;
    private String title;
    private String  ;
    private String content;
    private List<String> tag;

}
