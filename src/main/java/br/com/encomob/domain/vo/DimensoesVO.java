package br.com.encomob.domain.vo;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DimensoesVO {
    @Column(nullable = false)
    private double altura;
    @Column(nullable = false)
    private double largura;
    @Column(nullable = false)
    private double profundidade;

}
