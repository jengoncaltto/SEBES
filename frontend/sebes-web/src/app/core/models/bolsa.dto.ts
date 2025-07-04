export class BolsaDTO {
    constructor(
      private nome: String,
      private descricao: String,
      private valor: Number
    ) {}

    getNome(): String {
      return this.nome;
    }

    setNome(value: String) {
      this.nome = value;
    }

    getDescricao(): String {
      return this.descricao;
    }

    setDescricao(value: String) {
        this.descricao = value;
    }

    getValor (){
      return this.valor;
    }

    setValor(value: Number) {
        this.valor = value;
    }
}