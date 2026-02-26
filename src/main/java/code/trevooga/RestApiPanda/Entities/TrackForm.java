package code.trevooga.RestApiPanda.Entities;

import jakarta.validation.constraints.NotEmpty;

public class TrackForm {
    @NotEmpty(message = "Трек-номер не может быть пустым")
    private String trackNumber;

    // Геттер и сеттер
    public String getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }
}

