<template>
    <v-app>
        <v-card>
            <v-card-title>
                Leilões Cadastrados
                <v-spacer></v-spacer>
                <v-text-field
                        v-model="search"
                        append-icon="mdi-magnify"
                        label="Pesquisar por um Leilão"
                        single-line
                        hide-details
                ></v-text-field>
            </v-card-title>
            <v-data-table
                    no-results-text="Nenhum resultado encontrado"
                    no-data-text="Nenhum Leilão cadastrado"
                    :headers="headers"
                    :items="leiloes"
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
                    {text: 'Total do Leilão', value: 'totalDoLeilao'}
                ],
                leiloes: [],
                editedItem: {},
                defaultItem: {}
            }
        },
        mounted() {
            let api = baseURL + '/leiloes';
            axios.get(api).then(res => {
                let leiloes = res.data
                for (let i = 0; i < leiloes.length; i++) {
                    let leilao = leiloes[i];
                    let idEmpresa = leilao.vendedor;
                    leilao.totalDoLeilao = 0
                    this.leiloes.push(leilao);
                    axios.get(baseURL + "/empresa/" + idEmpresa).then(response => {
                        if (response.status == 200) {
                            let razaoSocialVendedor = response.data.razaoSocial;
                            leilao.razaoSocialVendedor = razaoSocialVendedor
                            leilao.inicioPrevisto = this.formatarData(new Date(leilao.inicioPrevisto));
                        }
                    });
                    if (i == leiloes.length - 1)
                        this.calcularTotalLeilao()
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
            },
            calcularTotalLeilao() {
                axios.get(baseURL + "/lotes/").then(response => {
                    if (response.status == 200) {
                        let lotes = response.data;
                        for (let j = 0; j < lotes.length; j++) {
                            let lote = lotes[j];
                            let leilaoId = lote.leilaoId;
                            for (let i = 0; i < this.leiloes.length; i++) {
                                let leilao = this.leiloes[i];
                                let id = leilao.id;
                                if (id == leilaoId) {
                                    let quantidade = lote.quantidade;
                                    let valorInicial = lote.valorInicial;
                                    if (valorInicial == null)
                                        valorInicial = 0;
                                    let resultado = quantidade * valorInicial;
                                    leilao.totalDoLeilao += resultado
                                    break
                                }
                            }
                        }
                        for (let i = 0; i < this.leiloes.length; i++) {
                            let leilao = this.leiloes[i];
                            let total = parseFloat(leilao.totalDoLeilao)
                            total = this.formatarMoeda(total + "00");
                            if (total == '0') total = "0,00"
                            leilao.totalDoLeilao = 'R$ ' + total
                        }
                    }
                });
            },
            formatarMoeda(valor) {
                valor = valor + '';
                valor = parseInt(valor.replace(/[\D]+/g, ''));
                valor = valor + '';
                valor = valor.replace(/([0-9]{2})$/g, ",$1");

                if (valor.length > 6) {
                    valor = valor.replace(/([0-9]{3}),([0-9]{2}$)/g, ".$1,$2");
                }
                return valor;
            }
        }
    }
</script>

<style>
    .v-data-table theme--light {
        padding: 5% !important;
    }
</style>