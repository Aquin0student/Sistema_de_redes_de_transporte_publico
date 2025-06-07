import pandas as pd

def extract_stations(stops_file, vertices_file, output_file='output_stations.csv'):
    # Passo 1: Ler vertices.csv para obter stop_id desejados
    vertices_df = pd.read_csv(vertices_file)
    desired_stop_ids = set(vertices_df['stop_id'].str.strip())

    # Passo 2: Ler stops.txt e filtrar apenas stop_id, stop_lat, stop_lon, stop_name
    stops_df = pd.read_csv(stops_file, usecols=['stop_id', 'stop_lat', 'stop_lon', 'stop_name'])

    # Passo 3: Filtrar estações que estão em desired_stop_ids
    filtered_df = stops_df[stops_df['stop_id'].isin(desired_stop_ids)]

    # Passo 3.1: Remover duplicatas com base no stop_id (mantém a primeira ocorrência)
    filtered_df = filtered_df.drop_duplicates(subset='stop_name')

    # Passo 4: Salvar resultado em CSV
    filtered_df.to_csv(output_file, index=False)
    print(f"Resultado salvo em {output_file}")

    # Retornar o DataFrame filtrado para uso posterior
    return filtered_df

if __name__ == "__main__":
    try:
        # Substitua pelos caminhos reais dos arquivos
        stops_file = "src/main/resources/stops.txt"
        vertices_file = "src/main/resources/vertices.csv"
        result_df = extract_stations(stops_file, vertices_file)

        # Exibir os primeiros resultados no console
        for _, station in result_df.head(5).iterrows():
            print(f"stop_id: {station['stop_id']}, stop_lat: {station['stop_lat']}, stop_lon: {station['stop_lon']}, stop_name: {station['stop_name']}")

    except Exception as e:
        print(f"Erro ao processar arquivos: {e}")
