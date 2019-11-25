import { Component, OnInit } from '@angular/core';
import { Tema } from 'src/app/models/tema.model';
import { CargoService } from 'src/app/services/service.index';
import { Router } from '@angular/router';
import swal from 'sweetalert'
import { Actividad } from 'src/app/models/actividad.model';

@Component({
  selector: 'app-tema',
  templateUrl: './tema.component.html',
  styleUrls: ['./tema.component.css']
})
export class TemaComponent implements OnInit {

  public _listaTemas: Tema[];
  public _listaActividad: Actividad[];
  public cantidad: number;
  public p: number = 1;
  public paginador: any;

  actualizar = false;
  _habilitar = true;
  _check = false;

  constructor(public _serviceCargo: CargoService,
    public _router: Router) {
    this._listaTemas = [];
    this._listaActividad = [];
  }

  ngOnInit() {
    this.findAllActividad();
  }

  findAllActividad() {
    this._serviceCargo.findAllActividades()
      .subscribe(res => {
        this._listaActividad = res;
        console.log(this._listaActividad);
      })
  }

  cambiarListaTemas(e: number) {
    if (e == 0) {
      this._listaTemas = [];
    }
    else {
      this.findAllTemasById(e);
    }
  }

  findAllTemasById(id: number) {

    this._serviceCargo.findOneTemasById(id)
      .subscribe(res => {
        this._listaTemas = res;
        if (this._listaTemas == null) {
          this._listaTemas = [];
          return;
        }
        console.log(this._listaTemas);
      })
  }

  deleteTema(id: number) {

    swal(`Una vez eliminado, no podrás recuperar este registro!`, {
      title: `Estas seguro?`,
      icon: `warning`,
      buttons: [`Cancel`, `Ok`],
      dangerMode: true
    })
      .then((willDelete) => {
        if (willDelete) {
          this._serviceCargo.deleteTemaById(id)
            .subscribe(data => {

              if (data.mensaje == `The DELETE statement conflicted with the REFERENCE constraint "FK__TBL_REUNI__FK_IC__3E52440B". The conflict occurred in database "REUNION", table "dbo.TBL_REUNIONES", column 'FK_ICOD_DOCENTE'.`) {
                swal(`Lo siento, no de puede borrar`, `El registro esta relacionado con la Tabla Reuniones`, {
                  icon: `error`,
                });
              } else if (data.mensaje == `El registro ha sido eliminado con éxito.`) {
                swal(`Buen Trabajo`, ` ${data.mensaje}`, {
                  icon: `success`,
                });
              }
            });

        } else {
          swal(`Informacion`, `El registro no fue eliminado!`, `info`);
        }
      });
  }
}
