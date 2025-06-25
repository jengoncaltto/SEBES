export class BolsaResumidaDTO {
    constructor(
      private nome: String,
      private descricao: String,
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

    getDescricaoResumida(): String{
        const limit = 95;
        if (this.descricao != '' && this.descricao.length > limit){
            return this.descricao.substring(0, limit) + '...';
        }
        return this.descricao;
    }

    setDescricao(value: String) {
        this.descricao = value;
    }
}
