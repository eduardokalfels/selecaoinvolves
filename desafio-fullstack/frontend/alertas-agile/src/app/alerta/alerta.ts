import { TipoAlertaEnum } from './tipo-alerta.enum';

export class Alerta {

    pontoDeVenda: string;
    descricao: string;
    produto: string;
    categoria: string;
    margem: number;
    tipo: TipoAlertaEnum;
}
