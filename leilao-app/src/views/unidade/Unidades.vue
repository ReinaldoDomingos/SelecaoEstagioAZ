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
                    :headers="headers"
                    :items="desserts"
                    :search="search"
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
                        text: 'Id',
                        align: 'start',
                        sortable: false,
                        value: 'id',
                    },
                    {text: 'Nome', value: 'nome'},
                ],
                desserts: []
            }
        },
        mounted() {
            let api = 'http://localhost:8081/unidades';
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