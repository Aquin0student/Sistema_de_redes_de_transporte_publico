import pandas as pd


df_stop_times = pd.read_csv("stop_times.txt")
df_trips = pd.read_csv("trips.txt")
df_arestas = pd.read_csv("arestas.csv")


df_stop_times = df_stop_times.merge(df_trips[['trip_id', 'route_id']], on='trip_id')


esperadas = []
for trip_id, grupo in df_stop_times.groupby("trip_id"):
    grupo = grupo.sort_values("stop_sequence").reset_index(drop=True)
    route_id = grupo["route_id"].iloc[0]
    for i in range(len(grupo) - 1):
        esperadas.append({
            "stop_id_origem": grupo.iloc[i]["stop_id"],
            "stop_id_destino": grupo.iloc[i + 1]["stop_id"],
            "route_id": route_id
        })

df_esperadas = pd.DataFrame(esperadas).drop_duplicates()


faltando = pd.merge(df_esperadas, df_arestas,
                    on=["stop_id_origem", "stop_id_destino", "route_id"],
                    how="left", indicator=True)

faltando = faltando[faltando["_merge"] == "left_only"]

print(f"Total de ligações faltando no grafo: {len(faltando)}")
print(faltando.head())
