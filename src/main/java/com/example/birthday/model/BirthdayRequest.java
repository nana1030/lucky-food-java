package com.example.birthday.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

/**
 * フロントエンドから受け取る生年月日データ
 */
public class BirthdayRequest {

    @NotNull(message = "生年月日を入力してください")
    @Past(message = "生年月日は過去の日付を指定してください")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    // --- Getter / Setter ---

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
