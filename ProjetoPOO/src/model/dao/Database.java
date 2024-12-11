package model.dao;

import model.dao.ConexaoMySQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database<T extends Identifiable> {

    private String tableName; // Nome da tabela do banco de dados.
    private Map<Integer, T> cache = new HashMap<>(); // Cache dos objetos mapeados.

    public Database(String tableName) {
        this.tableName = tableName; // Nome da tabela deve ser especificado.
    }

    // Método para adicionar uma nova entidade no banco
    public void insert(T datum, String[] columns, Object[] values) {
        String query = buildInsertQuery(tableName, columns);

        try (Connection conn = ConexaoMySQL.getConexao(); PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            // Atribuir os valores aos placeholders (?)
            for (int i = 0; i < values.length; i++) {
                stmt.setObject(i + 1, values[i]);
            }

            stmt.executeUpdate();

            // Obter o ID gerado automaticamente
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                datum.setID(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar um registro pelo ID
    public void delete(int id) {
        String query = "DELETE FROM " + tableName + " WHERE id = ?";

        try (Connection conn = ConexaoMySQL.getConexao(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar um registro pelo ID
    public void update(T datum, String[] columns, Object[] values) {
        String query = buildUpdateQuery(columns);

        try (Connection conn = ConexaoMySQL.getConexao(); PreparedStatement stmt = conn.prepareStatement(query)) {

            // Atribuir os valores aos placeholders (?)
            for (int i = 0; i < values.length; i++) {
                stmt.setObject(i + 1, values[i]);
            }

            // Adicionar o ID no final para a cláusula WHERE
            stmt.setInt(values.length + 1, datum.getID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obter um registro pelo ID
    public T getById(int id, RowMapper<T> mapper) {
        // Verifica se o objeto já está no cache
//        if (cache.containsKey(id)) {
//            T cacheData = cache.get(id);
//           
//            return cache.get(id);
//        }

        String query = "SELECT * FROM " + tableName + " WHERE id = ?";
        try (Connection conn = ConexaoMySQL.getConexao(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                T obj = mapper.mapRow(rs);
                cache.put(id, obj); // Armazena no cache
                return obj;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para obter todos os registros
    public List<T> getAll(RowMapper<T> mapper) {
        String query = "SELECT * FROM " + tableName;
        List<T> results = new ArrayList<>();
        try (Connection conn = ConexaoMySQL.getConexao(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
//                if (cache.containsKey(id)) {
//                    results.add(cache.get(id)); // Usa o cache se já estiver presente
//                } else {
                T obj = mapper.mapRow(rs);
                cache.put(id, obj); // Adiciona ao cache
                results.add(obj);
//                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    // Método para limpar o cache (se necessário)
    public void clearCache() {
        cache.clear();
    }

//    // Método auxiliar para construir a query INSERT
//    private String buildInsertQuery(String[] columns) {
//        StringBuilder query = new StringBuilder("INSERT INTO " + tableName + " (");
//        query.append(String.join(", ", columns));
//        query.append(") VALUES (");
//        query.append("?, ".repeat(columns.length));
//        query.setLength(query.length() - 2); // Remove a vírgula extra
//        query.append(")");
//        return query.toString();
//    }
    private String buildInsertQuery(String tableName, String[] columns) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ").append(tableName).append(" (");
        query.append(String.join(", ", columns));
        query.append(") VALUES (");

        // Alternativa para repetir a string
        for (int i = 0; i < columns.length; i++) {
            query.append("?, ");
        }
        query.setLength(query.length() - 2); // Remove a vírgula extra no final
        query.append(")");

        return query.toString();
    }

    // Método auxiliar para construir a query UPDATE
    private String buildUpdateQuery(String[] columns) {
        StringBuilder query = new StringBuilder("UPDATE " + tableName + " SET ");
        for (String column : columns) {
            query.append(column).append(" = ?, ");
        }
        query.setLength(query.length() - 2); // Remove a vírgula extra
        query.append(" WHERE id = ?");
        return query.toString();
    }

    // Interface para mapear resultados para objetos
    public interface RowMapper<T> {

        T mapRow(ResultSet rs) throws SQLException;
    }
}
