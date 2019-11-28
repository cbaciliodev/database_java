import { Tema } from './tema.model';
import { Docente } from './docente.model';

export class Asignado {


    constructor(

        public fk_ICOD_TEMA: number,
        public fk_ICOD_DOCENTE: number,
        public dfec_ASIGNADO: string,
        public tema: Tema,
        public docente: Docente
    ) { }
}
