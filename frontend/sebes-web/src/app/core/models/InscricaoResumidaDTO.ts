export class InscricaoResumidaDTO {
    constructor(
      private nomeDocumento: string,
      private status:string,
    ) {}

    getNomeDocumento(): string {
      return this.nomeDocumento;
    }

    setNomeDocumento(value: string) {
      this.nomeDocumento = value;
    }

    getStatus(): string {
      return this.status;
    }

    setStatus(value: string) {
      this.status = value;
    }
}
