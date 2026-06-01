import java.time.LocalTime;
import java.util.Objects;

enum diaSemana {Lunes,Martes,Miércoles,Jueves,Viernes}

public class TramoHorario implements Comparable<TramoHorario>
{
	private diaSemana dia;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	
	public TramoHorario(String d,String h)
	{
		String tokens[] = h.split(":");
		int hh = Integer.parseInt(tokens[0]);
		int mm = Integer.parseInt(tokens[1]);
		
		this.dia = diaSemana.valueOf(d);
		this.horaInicio = LocalTime.of(hh,mm);
		this.horaFin = this.horaInicio.plusMinutes(55);
	}

	public diaSemana getDia() {
		return dia;
	}

	public void setDia(diaSemana dia) {
		this.dia = dia;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	@Override
	public String toString() {
		return dia + " desde " + horaInicio + " hasta " + horaFin ;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dia, horaFin, horaInicio);
	}

	// @Override
	// public boolean equals(Object obj) {
	// 	if (this == obj)
	// 		return true;
	// 	if (obj == null)
	// 		return false;
	// 	if (getClass() != obj.getClass())
	// 		return false;
	// 	TramoHorario other = (TramoHorario) obj;
	// 	return dia == other.dia && Objects.equals(horaFin, other.horaFin)
	// 			&& Objects.equals(horaInicio, other.horaInicio);
	// }



	@Override
	public int compareTo(TramoHorario o) 
	{
		if (this.dia == o.dia)
			return this.horaInicio.compareTo(o.horaInicio);
		else
			return this.dia.compareTo(o.dia);
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return (true);
		if (obj == null)
			return (false);
		if (getClass() != obj.getClass())
			return (false);

		TramoHorario other = (TramoHorario) obj;
		return (this.getDia() == other.getDia() 
				&& this.getHoraInicio().equals(other.getHoraInicio())
				&& this.getHoraFin().equals(other.getHoraFin()));
	}
}