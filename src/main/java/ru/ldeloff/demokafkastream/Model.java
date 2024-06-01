package ru.ldeloff.demokafkastream;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Model {
    private int id;
    private long time;
    public Model(int id) {
        this.id = id;
        this.time = System.currentTimeMillis();
    }
}
