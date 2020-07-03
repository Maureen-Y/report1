package com.api.report.bean;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder({"userID", "searchHistory", "timeStamp"})
public class Search implements Serializable {
        private static final long serialVersionUID = 4960164018835526029L;
        private String userID;
        private String searchHistory;
        private String timeStamp;

        public Search() {
            super();
        }

        public Search(String userID, String searchHistory, String timeStamp) {
            super();
            this.userID = userID;
            this.searchHistory = searchHistory;
            this.timeStamp = timeStamp;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getSearchHistory() {
            return searchHistory;
        }

        public void setSearchHistory(String searchHistory) {
            this.searchHistory = searchHistory;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }


        @Override
        public String toString() {
            return "Search [userID=" + userID + ", searchHistory=" + searchHistory + ", timeStamp=" + timeStamp + "]";
        }

}
