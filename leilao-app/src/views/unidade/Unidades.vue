<template>
    <v-app>
        <v-data-table :headers="headers" :items="items" sort-by="id" class="elevation-1"
                      :search="search" no-results-text="Nenhum resultado encontrado"
                      no-data-text="Nenhuma unidade cadstrada">
            <template v-slot:top>
                <v-card-title>
                    Unidades Cadastradas
                    <v-spacer></v-spacer>
                    <v-text-field
                            v-model="search"
                            append-icon="mdi-magnify"
                            label="Pesquisar por unidade"
                            single-line
                            hide-details
                    ></v-text-field>
                </v-card-title>
                <v-toolbar flat color="white">
                    <v-spacer></v-spacer>
                    <v-dialog v-model="dialog" max-width="500px">
                        <template v-slot:activator="{ on }">
                            <v-btn color="primary" dark class="mb-2" v-on="on">Nova Unidade</v-btn>
                        </template>
                        <v-card>
                            <v-card-title>
                                <span class="headline">{{ formTitle }}</span>
                            </v-card-title>

                            <v-card-text>
                                <v-container>
                                    <v-row>
                                        <v-col cols="12" sm="12" md="12">
                                            <v-text-field v-model="editedItem.nome"
                                                          label="Nome da Unidade"></v-text-field>
                                        </v-col>
                                    </v-row>
                                </v-container>
                            </v-card-text>
                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="blue darken-1" text @click="close">Cancelar</v-btn>
                                <v-btn color="blue darken-1" text @click="save">Salvar</v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>
                </v-toolbar>
            </template>
            <template v-slot:item.actions="{ item }">
                <v-icon small class="mr-2" @click="editItem(item)">mdi-pencil</v-icon>
                <v-icon small color="" @click="deleteItem(item)">mdi-delete</v-icon>
            </template>
            <template v-slot:no-data>
                <v-btn color="primary" @click="iniciar">
                    <v-icon class="mr-2">mdi-reload</v-icon>
                    Recarregar
                </v-btn>
            </template>
        </v-data-table>
    </v-app>
</template>


<script>
    import axios from "axios";


    const baseURL = 'http://localhost:8081'
    export default {
        data: () => ({
            search: '',
            dialog: false,
            headers: [
                {
                    text: 'Nome',
                    align: 'start',
                    sortable: false,
                    value: 'nome',
                },
                {text: 'Ações', value: 'actions'},
            ],
            items: [],
            editedIndex: -1,
            editedItem: {},
            defaultItem: {
                nome: '',
            },
        }),

        computed: {
            formTitle() {
                return this.editedIndex === -1 ? 'Novo Unidade' : 'Editar Unidade'
            },
        },

        watch: {
            dialog(val) {
                val || this.close()
            },
        },

        created() {
            this.iniciar()
        },

        methods: {
            iniciar() {
                let api = baseURL + '/unidades';
                axios.get(api).then(res => {
                    this.items = res.data;
                }).catch(err => {
                    console.log(err.response);
                });
            },

            editItem(item) {
                this.editedIndex = this.items.indexOf(item)
                this.editedItem = Object.assign({}, item)
                this.dialog = true
            },

            deleteItem(item) {
                const index = this.items.indexOf(item)
                let id = this.items[index].id;
                confirm('Tem certeza de que deseja excluir este item?')
                && this.items.splice(index, 1)
                && axios.delete(baseURL + "/unidades/" + id)
            },

            close() {
                this.dialog = false
                setTimeout(() => {
                    this.editedItem = Object.assign({}, this.defaultItem)
                    this.editedIndex = -1
                }, 300)
            },

            save() {
                if (this.editedIndex > -1) {
                    Object.assign(this.items[this.editedIndex], this.editedItem)
                    let unidade = this.items[this.editedIndex];
                    axios.put(baseURL + "/unidades/" + unidade.id, JSON.stringify(unidade), {
                        headers: {
                            "Access-Control-Allow-Origin": "*",
                            "Access-Control-Allow-Methods": "GET, POST, PATCH, PUT, DELETE, OPTIONS",
                            "Access-Control-Allow-Headers": "Origin, Content-Type, X-Auth-Token",
                            'Content-Type': 'application/json',
                        },
                    })
                } else {
                    this.items.push(this.editedItem)
                    axios.post(baseURL + "/unidades/" , JSON.stringify(this.editedItem), {
                        headers: {
                            "Access-Control-Allow-Origin": "*",
                            "Access-Control-Allow-Methods": "GET, POST, PATCH, PUT, DELETE, OPTIONS",
                            "Access-Control-Allow-Headers": "Origin, Content-Type, X-Auth-Token",
                            'Content-Type': 'application/json',
                        },
                    })
                }
                this.close()
                setTimeout(() => {
                    this.iniciar()
                }, 300)
            },
        },
    }
</script>

<style>
    .v-data-table theme--light {
        padding: 5% !important;
    }
</style>