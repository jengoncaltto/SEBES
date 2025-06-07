export class criarBolsaDTO {
  constructor(
    public nome: string
  ) {}

  formatarNome(): string {
    return this.email.trim().toLowerCase();
  }
}
