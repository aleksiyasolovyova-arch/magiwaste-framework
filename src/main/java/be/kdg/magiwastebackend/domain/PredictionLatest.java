package be.kdg.magiwastebackend.domain;


import jakarta.persistence.*;
import java.time.OffsetDateTime;

import java.time.Instant;

@Entity
@Table(name = "bin_prediction_ttf")
public class PredictionLatest {

    @Id
    @Column(name = "bin_id")
    private Long binId;

    @Column(name = "predicted_hours", nullable = false)
    private double predictedHours;

    @Column(name = "predicted_at", nullable = false)
    private Instant predictedAt;

    @Column(name = "method")
    private String method;

    @Column(name = "model_mae")
    private Double modelMae;

    @Column(name = "model_version")
    private String modelVersion;

    public Long getBinId() { return binId; }
    public Double getPredictedHours() { return predictedHours; }
    public Instant getPredictedAt() { return predictedAt; }
    public String getMethod() { return method; }
    public Double getModelMae() { return modelMae; }
    public String getModelVersion() { return modelVersion; }
}
