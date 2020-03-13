<template>
    <v-app>
        <v-card>
            <v-card-title>
                Unidades
                <v-spacer></v-spacer>
                <v-text-field
                        v-model="search"
                        append-icon="mdi-magnify"
                        label="Search"
                        single-line
                        hide-details
                ></v-text-field>
            </v-card-title>
            <v-data-table
                    no-results-text="Nenhum resultado encontrado"
                    :headers="headers"
                    :items="desserts"
                    :search="search"
            ></v-data-table>
        </v-card>
    </v-app>
</template>


<script>
    import axios from 'axios'

    const baseURL = 'http://localhost:8081'
    export default {
        name: 'Home',
        data() {
            return {
                search: '',
                headers: [
                    {
                        text: 'Razão Social do Vendedor',
                        align: 'start',
                        sortable: false,
                        value: 'razaoSocialVendedor',
                    },
                    {text: 'Descrição', value: 'descricao'},
                    {text: 'Inicio Previsto', value: 'inicioPrevisto'},
                    {text: 'Total do Leilão', value: 'TotalDoLeilao'}
                ],
                desserts: [],
                editedItem: {},
                defaultItem: {}
            }
        },
        mounted() {
            let api = baseURL + '/leilao';
            axios.get(api).then(res => {
                let leiloes = res.data
                for (let i = 0; i < leiloes.length; i++) {
                    let leilao = leiloes[i];
                    let idEmpresa = leilao.vendedor;
                    axios.get(baseURL + "/empresa/" + idEmpresa).then(response => {
                        if (response.status == 200) {
                            let razaoSocialVendedor = response.data.razaoSocial;
                            leilao.razaoSocialVendedor = razaoSocialVendedor
                            leilao.inicioPrevisto = this.formatarData(new Date(leilao.inicioPrevisto));
                            this.desserts.push(leilao);
                        }
                    });
                }
            }).catch(err => {
                console.log(err.response);
            });
        },
        methods: {
            formatarData(data) {
                let dia = data.getDate().toString(),
                    diaF = (dia.length == 1) ? '0' + dia : dia,
                    mes = (data.getMonth() + 1).toString(),
                    mesF = (mes.length == 1) ? '0' + mes : mes,
                    anoF = data.getFullYear();
                return diaF + "/" + mesF + "/" + anoF;
            }
        }
    }
</script>

<style>
    .v-data-table theme--light {
        padding: 5% !important;
    }
</style>