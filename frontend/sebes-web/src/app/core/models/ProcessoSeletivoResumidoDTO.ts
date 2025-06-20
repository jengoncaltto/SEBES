export class ProcessoSeletivoDTO {
    constructor(
      private nome: string,
      private periodo: string,
      private status:string,

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

    setStatus(value: string) {
      this.status = value;
    }

    getPeriodo(): string {
      return this.periodo;
    }

    setPeriodo(value: string) {
        this.periodo = value;
    }
}