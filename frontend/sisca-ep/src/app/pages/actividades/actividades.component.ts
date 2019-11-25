import { Component, OnInit } from '@angular/core';
import { Actividad } from 'src/app/models/actividad.model';
import { CargoService } from 'src/app/services/service.index';
import swal from 'sweetalert';

@Component({
  selector: 'app-actividades',
  templateUrl: './actividades.component.html',
  styleUrls: ['./actividades.component.css']
})
export class ActividadesComponent implements OnInit {

  public cantidad: number;
  public p: number = 1;
  public paginador: any;

  public _listaActividad: Actividad[];

  constructor(public _serviceCargo: CargoService) { }

  ngOnInit() {

    this.findAllActividad();
  }

  findAllActividad() {

    this._listaActividad = [];
    this._serviceCargo.findAllActividades()
      .subscribe(res => {
        this._listaActividad = res;
      })
  }

  deleteActividad(id: number) {

    swal(`Una vez eliminado, no podrás recuperar este registro!`, {
      title: `Estas seguro?`,
      icon: `warning`,
      buttons: [`Cancel`, `Ok`],
      dangerMode: true
    })
      .then((willDelete) => {
        if (willDelete) {
          this._serviceCargo.deleteActividadById(id)
            .subscribe(data => {

              if (data.mensaje == "The DELETE statement conflicted with the REFERENCE constraint \"FK__TBL_DOCEN__FK_IC__398D8EEE\". The conflict occurred in database \"REUNION\", table \"dbo.TBL_DOCENTES\", column 'FK_ICOD_CARGO'.") {
                swal(`Lo siento, no de puede borrar`, `El registro esta relacionado con la Tabla docente`, {
                  icon: `error`,
                });
              } else if (data.mensaje == `El registro ha sido eliminado con éxito.`) {
                swal(`Buen Trabajo`, ` ${data.mensaje}`, {
                  icon: `success`,
                });
              }
              this.findAllActividad();
            });

        } else {
          swal(`Informacion`, `El registro no fue eliminado!`, `info`);
        }
      });
  }
}