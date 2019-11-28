import { Cargo } from './cargo.model'

export class Docente {


    constructor(
        public icod_DOCENTE: number,
        public vnombre_DOCENTE: string,
        public vapaterno_DOCENTE: string,
        public vamaterno_DOCENTE: string,
        public cargo: Cargo,
        public vdni_DOCENTE: string
    ) { }
}