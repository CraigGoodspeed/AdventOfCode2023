package nz.co.goodspeed;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.OffsetDateTime;

public class SupportStuffs {

    public static void main(String[] args) throws JsonProcessingException {

        OffsetDateTime effectiveDateTime = OffsetDateTime.now();
        String objectNoEnd = "{\"startDateTime\":\"2023-12-09T00:00:00Z\", \"something\":\"no end\"}";
        String objectNoStart = "{\"startEndTime\":\"2023-12-09T00:00:00Z\", \"something\":\"no end\"}";
        ObjectMapper mapper= mapper();
        //effectiveDateTime.isBefore(null);
        MyModel noEnd = mapper.readValue(objectNoEnd, MyModel.class);
        MyModel noStart = mapper.readValue(objectNoEnd, MyModel.class);
        System.out.println("asdas");
    }

    public  static ObjectMapper mapper() {
        return (new ObjectMapper())
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false)
                .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
    }

    static class MyModel {
        OffsetDateTime startDateTime;
        OffsetDateTime endDateTime;
        String something;

        public MyModel() {
        }

        public MyModel(OffsetDateTime startDateTime, OffsetDateTime endDateTime, String something) {
            this.startDateTime = startDateTime;
            this.endDateTime = endDateTime;
            this.something = something;
        }

        public OffsetDateTime getStartDateTime() {
            return startDateTime;
        }

        public void setStartDateTime(OffsetDateTime startDateTime) {
            this.startDateTime = startDateTime;
        }

        @JsonProperty(required = true)
        public OffsetDateTime getEndDateTime() {
            return endDateTime;
        }

        public void setEndDateTime(OffsetDateTime endDateTime) {
            this.endDateTime = endDateTime;
        }

        public String getSomething() {
            return something;
        }

        public void setSomething(String something) {
            this.something = something;
        }
    }
}
