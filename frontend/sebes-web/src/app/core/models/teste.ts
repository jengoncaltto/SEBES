export class testeDTO {
    constructor(
      private nome: string,
      private status: string,
    ) {}

    getNome(): string {
      return this.nome;
    }

    setNome(value: string) {
      this.nome = value;
    }

    getStatus(): string {
      return this.status;
    }

    getDescricaoResumida(): string{
        const limit = 95;
        if (this.status != '' && this.status.length > limit){
            return this.status.substring(0, limit) + '...';
        }
        return this.status;
    }

    setStatus(value: string) {
        this.status = value;
    }
}
