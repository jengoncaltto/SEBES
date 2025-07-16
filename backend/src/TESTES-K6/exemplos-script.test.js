import http from 'k6/http';
import { check,sleep } from 'k6';
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";

//Ir até pasta de teste : cd backend/src/TESTES-K6
//Comando resultado em JSON : k6 run exemplos-script.test.js --out json=resultados-json/resultado.json   
//Comando resultado em Web-Dashboard : k6.exe run --out web-dashboard=report.html exemplos-script.test.js

//Configurações de número de virtual users(VUS) e duração das fases de teste
export let options = {
    stages: [
    
    { duration: '30s', target: 20 }, //Ramp-Up
    { duration: '1m', target: 20 },  // Mantém por 1 min
    { duration: '30s', target: 40 }, // Pico 
    { duration: '1m', target: 40 },  // Sustenta o pico
    { duration: '1m', target: 0 },  // Reduz para 0
     
    //Teste de chamada, conferir o funcionamento do endpoint
    //{ duration: '10s', target: 10 },

  ]
};

//Cada VUS executará a função simultaneamente
export default function (){
    //Seleção de qual requisição testar
    testeGETBolsas();
    /*
    
    testePOSTBolsas();
    */
   //Extra
    //testeGETDiscente();
}

export function testeGETBolsas(){
    let res = http.get('https://sebes-weck.onrender.com/api/bolsas');

    //Verifica a resposta do servidor
    check(res, {
        //200: resposta geral de sucesso
        'status é 200': r =>r.status === 200,
        //se o tempo total da requisição for maior que o especificado, a verificação falha
        'latência < 500ms': (r) => r.timings.duration < 500,
    });
    
    //Pausa de 1s até a próxima requisição
    sleep(1);
       
}

export function testeGETDiscente(){
    let res = http.get('https://sebes-weck.onrender.com/api/discentes');

    //Verifica a resposta do servidor
    check(res, {
        //200: resposta geral de sucesso
        'status é 200': r =>r.status === 200,
        //se o tempo total da requisição for maior que o especificado, a verificação falha
        'latência < 500ms': (r) => r.timings.duration < 500,
    });

    //Pausa de 1s até a próxima requisição
    sleep(1);
       
}

export function testePOSTBolsas(){
    //Constante utilizada para facilitar testes
    const payload = JSON.stringify({
    nome: 'Bolsa Teste',
    descricao: 'Descrição Teste',
    periodo: '12',
    valor: 1000
  });
  
  //Define o tipo de conteúdo do corpo da requisição
  const headers = { 'Content-Type': 'application/json' };

  const res = http.post('https://sebes-weck.onrender.com/api/cadastrar', payload, { headers });

  check(res, {
    //201 indica que o POST foi bem sucedido
    'status é 201': (r) => r.status === 201,
    'latência < 500ms': (r) => r.timings.duration < 500,
  });
  //Pausa de 1s até a próxima requisição
    sleep(1);

}



//Configurações  do Dashboard
export function handleSummary(data) {
  console.log('Preparing the end-of-test summary...');
  return {
      'report/test-summary-report.html': htmlReport(data),
  };
}  