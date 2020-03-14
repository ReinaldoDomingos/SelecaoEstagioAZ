<template>
    <v-app>
        <v-card>
            <v-card-title>
                Empresas Cadastradas
                <v-spacer></v-spacer>
                <v-text-field
                        v-model="search"
                        append-icon="mdi-magnify"
                        label="Pesquisar por uma empresa"
                        single-line
                        hide-details
                ></v-text-field>
            </v-card-title>
            <v-data-table
                    :headers="headers"
                    :items="desserts"
                    :search="search"
                    no-results-text="Nenhum resultado encontrado"
                    no-data-text="Nenhuma Empresa cadastrado"
            ></v-data-table>
        </v-card>
    </v-app>
</template>


<script>
    import axios from 'axios'

    export default {
        name: 'Home',
        data() {
            return {
                search: '',
                headers: [
                    {
                        text: 'CNPJ',
                        align: 'start',
                        sortable: false,
                        value: 'cnpj',
                    },
                    {text: 'Razão Social', value: 'razaoSocial'},
                    {text: 'Telefone', value: 'telefone'},
                    {text: 'email', value: 'email'},
                ],
                desserts: []
            }
        },
        mounted() {
            let api = 'http://localhost:8081/empresas';
            axios.get(api, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                    "Access-Control-Allow-Methods": "GET, POST, PATCH, PUT, DELETE, OPTIONS",
                    "Access-Control-Allow-Headers": "Origin, Content-Type, X-Auth-Token",
                    'Content-Type': 'application/json',
                },
            }).then(res => {
                this.desserts = res.data;
            }).catch(err => {
                console.log(err.response);
            });
        }
    }
</script>

<style>
    .v-data-table theme--light {
        padding: 5% !important;
    }
</style>