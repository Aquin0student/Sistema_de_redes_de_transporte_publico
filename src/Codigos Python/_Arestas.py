import pandas as pd


df_stop_times = pd.read_csv("stop_times.txt")
df_trips = pd.read_csv("trips.txt")[["trip_id", "route_id"]]


df = df_stop_times.merge(df_trips, on="trip_id", how="left")



def converter_tempo(t):
    h, m, s = map(int, t.split(":"))
    return pd.Timedelta(hours=h, minutes=m, seconds=s)


df["departure_time"] = df["departure_time"].apply(converter_tempo)

arestas = []
arestas_unicas = set() 


for trip_id, grupo in df.groupby("trip_id"):
    grupo = grupo.sort_values("stop_sequence").reset_index(drop=True)
    route_id = grupo["route_id"].iloc[0]

    for i in range(len(grupo) - 1):
        origem = grupo.iloc[i]
        destino = grupo.iloc[i + 1]

        tempo_viagem = (destino["departure_time"] - origem["departure_time"]).total_seconds()

        if tempo_viagem <= 0:
            continue

        chave_aresta = (origem["stop_id"], destino["stop_id"], route_id)

        if chave_aresta not in arestas_unicas:
            arestas.append({
                "stop_id_origem": origem["stop_id"],
                "stop_id_destino": destino["stop_id"],
                "tempo_de_viagem_segundos": tempo_viagem,
                "route_id": route_id,
                "trip_id": trip_id 
            })
            arestas_unicas.add(chave_aresta)

df_arestas = pd.DataFrame(arestas)
df_arestas.to_csv("arestas.csv", index=False)
