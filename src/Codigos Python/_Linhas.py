import pandas as pd

# Caminho para a pasta onde estão os arquivos GTFS
caminho = "./" 

# Carregar arquivos GTFS
routes = pd.read_csv(caminho + "routes.txt")
trips = pd.read_csv(caminho + "trips.txt")
stop_times = pd.read_csv(caminho + "stop_times.txt")
stops = pd.read_csv(caminho + "stops.txt")

# Juntar trips com routes para saber a qual linha pertence cada viagem
routes_trips = pd.merge(trips, routes, on="route_id")

# Juntar com stop_times para pegar as estações e ordem
routes_trips_stops = pd.merge(routes_trips, stop_times, on="trip_id")

# Juntar com stops para pegar nomes e coordenadas das estações
full = pd.merge(routes_trips_stops, stops, on="stop_id")

# Para facilitar: filtrar colunas úteis
full = full[[
    "route_id", "route_short_name", "route_long_name",
    "trip_id", "stop_id", "stop_sequence", "stop_name", "stop_lat", "stop_lon"
]]

# Vamos imprimir as estações de cada linha, na ordem

# Agrupar por linha e por viagem, ordenar as estações por stop_sequence
grouped = full.groupby(["route_id", "trip_id"])

# Para evitar repetir estações em viagens diferentes da mesma linha,
# vamos pegar a primeira viagem de cada linha como referência

linhas = {}

for route_id in full["route_id"].unique():
    # pegar uma viagem da linha
    trips_da_linha = full[full["route_id"] == route_id]["trip_id"].unique()
    primeira_viagem = trips_da_linha[0]
    # pegar as estações da viagem ordenadas
    estacoes = full[(full["route_id"] == route_id) & (full["trip_id"] == primeira_viagem)]
    estacoes = estacoes.sort_values("stop_sequence")
    lista_estacoes = estacoes["stop_name"].tolist()

    nome_linha = estacoes["route_long_name"].values[0]
    linhas[nome_linha] = lista_estacoes

# Printar as linhas com suas estações
for linha, estacoes in linhas.items():
    print(f"\n{linha} ({len(estacoes)} estações):")
    print(" -> ".join(estacoes))
