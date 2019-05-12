import { TipoAlertaEnum } from './tipo-alerta.enum';

export class Alerta {

    pontoDeVenda: string;
    descricao: string;
    produto: string;
    categoria: string;
    margem: number;
    tipo: TipoAlertaEnum;

    get descricaoTipoAlerta() : string {
        if(this.tipo === TipoAlertaEnum.RUPTURA) {
            return "Ruptura"; 
        } else if(this.tipo === TipoAlertaEnum.PRECO) {
            return "Preço"; 
        } else if(this.tipo === TipoAlertaEnum.PARTICIPACAO) {
            return "Participação"; 
        }
    }

}
