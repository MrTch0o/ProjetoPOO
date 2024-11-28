//
//package model.dao;
//
//import java.util.Arrays;
//
//public class Database<T extends Identifiable>{
//    T[] data;
//    int currentID = 0;
//    
//    public Database(T[] arr){
//        arr = Arrays.copyOf(arr, 0);
//        data = arr;
//    }
//    
//    public void create(T datum){
//        this.currentID++;
//        datum.setID(currentID);
//        data = Arrays.copyOf(data, data.length + 1);
//        data[data.length - 1] = datum;
//    }
//    
//    public void delete(int id){
//        T[] temp = Arrays.copyOf(data, data.length - 1);
//        int deletePosition = -1;
//        
//        for(int i = 0; i < data.length; i++){
//            if(data[i].getID() == id){
//                deletePosition = i;
//            }
//        }
//        
//        if(deletePosition < 0){
//            return;
//        } 
//        int currentPosition = 0;
//        for(int i = 0; i < data.length; i++){
//            if(i != deletePosition){
//                temp[currentPosition] = data[i];
//                currentPosition++;
//            }
//        }
//        data = temp;
//    } 
//    
//    public T getById(int id){
//        T result = null;
//        for (int i = 0; i < data.length; i++) {
//            if (data[i].getID() == id) {
//                return data[i];
//            }
//        }
//        return result;
//    }
//    
//    public T[] getAll(){
//        return data;
//    }
//    
//    public void update(T datum){
//        for(int i = 0; i < data.length; i++){
//            if(datum.getID() == data[i].getID()){
//                data[i] = datum;
//            }
//        }
//    }
//}


package model.dao;

import java.util.ArrayList;
import java.util.List;

public class Database<T extends Identifiable> {
    private List<T> data;
    private int currentID = 0;

    public Database() {
        this.data = new ArrayList<>();
    }

    public void create(T datum) {
        this.currentID++;
        datum.setID(currentID);
        data.add(datum);
    }

    public void delete(int id) {
        data.removeIf(datum -> datum.getID() == id);
    }

    public T getById(int id) {
        for (T datum : data) {
            if (datum.getID() == id) {
                return datum;
            }
        }
        return null; // Retorna null se o ID não for encontrado
    }

    public List<T> getAll() {
        return new ArrayList<>(data); // Retorna uma cópia da lista
    }

    public void update(T datum) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getID() == datum.getID()) {
                data.set(i, datum);
                return; // Finaliza a atualização após encontrar o elemento
            }
        }
    }
    
    
}
