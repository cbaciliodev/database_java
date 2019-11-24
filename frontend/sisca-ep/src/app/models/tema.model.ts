import { Actividad } from './actividad.model';

export class Tema {


    constructor(
        
        public icod_TEMA: number,
        public vdesc_TEMA: string,
        public cest_TEMA: string,
        public actividad: Actividad

    ){}
}