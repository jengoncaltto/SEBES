export class BolsaResumidaDTO {
    constructor(
      private nome: string,
      private descricao: string,
    ) {}

    getNome(): string {
      return this.nome;
    }

    setNome(value: string) {
      this.nome = value;
    }

    getDescricao(): string {
      return this.descricao;
    }

    getDescricaoResumida(): string{
        const limit = 95;
        if (this.descricao != '' && this.descricao.length > limit){
            return this.descricao.substring(0, limit) + '...';
        }
        return this.descricao;
    }

    setDescricao(value: string) {
        this.descricao = value;
    }
}
