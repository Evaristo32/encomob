package br.com.encomob.domain;

import br.com.encomob.domain.enums.Categoria;
import br.com.encomob.domain.enums.StatusDisponibilidade;
import br.com.encomob.domain.vo.DimensoesVO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table( name = "moveis")
public class Movel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 500)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;

    @Column(nullable = false)
    private String material;

    @Column(nullable = false)
    private String cor;

    @Embedded
    private DimensoesVO dimensoesVO;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(name = "quantidade_estoque", nullable = false)
    private int quantidadeEstoque;

    @ElementCollection
    @CollectionTable(name = "imagens_movel", joinColumns = @JoinColumn(name = "movel_id"))
    @Column(name = "imagem")
    private List<String> imagens;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private Fornecedor fornecedor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusDisponibilidade status;
}
