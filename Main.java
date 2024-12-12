package CRUD_JPA_jugadoresEquipos;

import jakarta.persistence.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Jugadores");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //TRABJANDO CON LA TABLA DE JUGADORES
        tx.begin();
        Jugador jugador1 = em.find(Jugador.class, 1);    //Buscamos al jugador con id 1;
        tx.commit();

        System.out.println("MOSTRAMOS AL JUGADOR POR ID---------------------------------------------------------");
        System.out.println(jugador1);                       //Mostramos al jugador
        System.out.println("---------------------------------------------------------");


        //Jugador jugador2 = new Jugador("nuevoJugador", 1.74f, 72f, equipo1);
        //tx.begin();
        //em.persist(jugador2);               //Añadimos a la base el nuevo jugador
        //tx.commit();

        //System.out.println("MOSTRAMOS AL JUGADOR AÑADIDO---------------------------------------------------------");
        //System.out.println(jugador2);       //Mostramos el jugador añadido
        //System.out.println("---------------------------------------------------------");


        Jugador jugadorMod = em.find(Jugador.class, 2);           //Buscamos al jugador con id 2
        jugadorMod.setNombre("Nombre modificado");                   //Modificamos algunos parámetros
        jugadorMod.setEstatura(1.90f);

        tx.begin();
        em.merge(jugadorMod);                                        //Hacemos el update del jugador
        tx.commit();

        System.out.println("MOSTRAMOS AL JUGADOR MODIFICADO---------------------------------------------------------");
        System.out.println(jugadorMod);                              //Mostramos el jugador modificado
        System.out.println("---------------------------------------------------------");


        //Jugador jugador18 = em.find(Jugador.class, 18);     //Buscamos al jugador con id 18
        //tx.begin();
        //em.remove(jugador18);                                  //Eliminamos el jugador con id 18
        //tx.commit();


        tx.begin();
        //Una manera de hacerlo
        TypedQuery<Jugador> listaJugadores = em.createQuery("SELECT j FROM Jugador j", Jugador.class);      //Creamos una query
        //System.out.println(listaJugadores.getResultList());


        //Otra manera de hacerlo
        List<Jugador> jugadores = em.createQuery("SELECT j FROM Jugador j ORDER BY j.id DESC", Jugador.class).getResultList();

        System.out.println("MOSTRAMOS A TODOS LOS JUGADORES---------------------------------------------------------");
        for (Jugador jugador : jugadores) {
            System.out.println(jugador);
        }
        tx.commit();
        System.out.println("---------------------------------------------------------");

        //TRABAJANDO CON LA TABLA DE EQUIPOS

        tx.begin();
        Equipo equipo2 = em.find(Equipo.class, 5);      //Buscamos el equipo con id 5
        tx.commit();

        System.out.println("MOSTRAMOS EL EQUIPO POR ID---------------------------------------------------------");
        System.out.println(equipo2);                       //Mostramos al equipo con id 5
        System.out.println("---------------------------------------------------------");



        //tx.begin();
        //Equipo equipoNuevo = new Equipo("Nombre equipo", "Nombre estadio");      //Creamos un equipo nuevo
        //em.persist(equipoNuevo);                                                                //Añadimos el equipo nuevo
        //tx.commit();

        //System.out.println("MOSTRAMOS EL EQUIPO AÑADIDO---------------------------------------------------------");
        //System.out.println(equipoNuevo);                                                        //Mostramos el equipo nuevo
        //System.out.println("---------------------------------------------------------");


        tx.begin();
        Equipo equipoMod = em.find(Equipo.class, 9);            //Buscamos al equipo con id 9
        equipoMod.setNombre("Nombre estadio modificado");          //Modificamos los campos del equipo
        equipoMod.setEstadio("Nombre estadio modificado");
        em.merge(equipoMod);
        tx.commit();

        System.out.println("MOSTRAMOS EL EQUIPO MODIFICADO---------------------------------------------------------");
        System.out.println(equipoMod);                             //Mostramos el equipo modificado
        System.out.println("---------------------------------------------------------");


        //tx.begin();
        //Equipo equipoEliminar = em.find(Equipo.class, 10);      //Buscamos el equipo con id 10
        //em.remove(equipoEliminar);                                 //Eliminamos el equipo
        //tx.commit();


        tx.begin();
        List<Equipo> equipos = em.createQuery("SELECT e FROM Equipo e ORDER BY e.id DESC", Equipo.class).getResultList();      //Creamos una query

        System.out.println("MOSTRAMOS TODOS LOS EQUIPOS---------------------------------------------------------");
        for (Equipo e : equipos) {
            System.out.println(e);                                                                             //Mostramos el resultado de la query
        }
        tx.commit();
        System.out.println("---------------------------------------------------------");
    }
}
