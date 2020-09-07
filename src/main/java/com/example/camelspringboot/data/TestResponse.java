package com.example.camelspringboot.data;

/**
 * @author Orlov Diga
 */
public class TestResponse {
    private String text;
    private String resultType;
    private String expressionText;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getExpressionText() {
        return expressionText;
    }

    public void setExpressionText(String expressionText) {
        this.expressionText = expressionText;
    }

    @Override
    public String toString() {
        return "TestResponse{" +
                "text='" + text + '\'' +
                ", resultType='" + resultType + '\'' +
                ", expressionText='" + expressionText + '\'' +
                '}';
    }
}
